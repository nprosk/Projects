#!/usr/bin/env python3

import argparse
import queue
import socket
import ssl
from html.parser import HTMLParser


# Parser Class that extracts Urls and Secret Flags
class LinkParser(HTMLParser):

    def __init__(self):
        HTMLParser.__init__(self)
        self.links = []
        self.flag = False
        self.flags = []

    def handle_starttag(self, tag, attrs):
        # Appends Urls to the links list
        if (tag == "a"):
            self.links.append(attrs[0][1])
        # If an h2 tag, initiates secret flag check sequence
        if (tag == "h2") and len(attrs) > 0:
            self.flag = True

    def returnLinks(self):
        return self.links

    def returnFlags(self):
        return self.flags

    # Gets the secret flag and adds it to secret flag list
    def handle_data(self, data):
        if self.flag:
            self.flags.append(data[6:])
            self.flag = False


DEFAULT_SERVER = "proj5.3700.network"
DEFAULT_PORT = 443


# Crawler class that logs in to webserver and crawls to find secret flags
class Crawler:
    def __init__(self, args):
        self.server = args.server
        self.port = args.port
        self.username = args.username
        self.password = args.password
        self.session_id = None
        self.csrf_token = None

        self.visited = set()
        self.discovered = queue.Queue()
        self.secret_flags = []
        self.messages = 0

    # Logs into Fakebook
    def login_sequence(self):
        # Gets necessary information from login page
        login = self.send_request("GET /accounts/login/  HTTP/1.1\r\nHost: proj5.3700.network\r\nConnection: close\r\n\r\n")
        loginList = login.split("\r\n")
        for element in loginList:
            if element.__contains__("csrftoken="):
                csrf = ((element.split("csrftoken="))[1].split(";"))[0]
            if element.__contains__("sessionid="):
                sessionid = ((element.split("sessionid="))[1].split(";"))[0]
            if element.__contains__("\"csrfmiddlewaretoken\" value="):
                middleware = ((element.split("\"csrfmiddlewaretoken\" value=\"")))[1].split("\"")[0]

        # POST message to login to Fakebook
        payload = "username=" + self.username + "&password=" + self.password + "&csrfmiddlewaretoken=" + middleware + "&next=\r\n\r\n"
        content_length = str(len(payload))
        content_type = "application/x-www-form-urlencoded"
        connection = "close"
        referrer = self.server + "/accounts/login/"
        prerequest = "POST /accounts/login/ HTTP/1.1\r\nHost: " + self.server + \
                     "\r\ncookie: csrftoken=" + csrf + "; sessionid=" + sessionid + \
                     "\r\n" + "Content-Type: " + content_type + "\r\n" + "Connection: " + connection\
                     + "\r\n" + "Content-length: " + content_length + "\r\n" + "Referrer: " + referrer + "\r\n\r\n"

        # Store cookies in crawler fields
        response = self.send_request(prerequest + payload)
        responseList = response.split("\r\n")
        for element in responseList:
            if element.__contains__("csrftoken="):
                csrf = ((element.split("csrftoken="))[1].split(";"))[0]
            if element.__contains__("sessionid="):
                sessionid = ((element.split("sessionid="))[1].split(";"))[0]

        self.csrf_token = csrf
        self.session_id = sessionid

    # Crawls through webpages using DFS search strategy to find secret flags
    def crawl(self):
        # Initialize DFS
        self.send_request(
            "GET / HTTP/1.1\r\nHost: proj5.3700.network\r\ncookie: csrftoken=" + self.csrf_token + "; sessionid=" + self.session_id + "\r\nConnection: close\r\n\r\n")
        self.visited.add("/accounts/logout/")
        self.visited.add("/")
        self.discovered.put("/fakebook/")

        # DFS loop
        while not self.discovered.empty():
            # Get new URL and add to visited
            url = self.discovered.get()
            if url in self.visited:
                continue
            self.visited.add(url)

            # GET response
            response = self.send_request(
                "GET " + url + " HTTP/1.1\r\nHost: proj5.3700.network\r\ncookie: csrftoken=" + self.csrf_token + "; sessionid=" + self.session_id + "\r\nConnection: close\r\n\r\n")

            # If OK, parse for URL's and check for secret flags
            if response[9:12] == "200":
                parser = LinkParser()
                parser.feed(response)
                responseList = parser.returnLinks()

                flags = parser.returnFlags()
                if not len(flags) == 0:
                    for item in flags:
                        if item not in self.secret_flags:
                            self.secret_flags.append(item)
                if len(self.secret_flags) >= 5: # Finish sequence when all flags are retrieved
                    for flag in self.secret_flags:
                        print(flag)
                    return

                for elem in responseList:
                    if elem not in self.visited:
                        self.discovered.put(elem)

            # Error Handling
            elif response[9:12] == "302":
                newLoc = response.split("Location: ")[1].split("\r\n")[0]
                self.discovered.put(newLoc)
            elif response[9:12] == "503":
                self.visited.remove(url)
                self.discovered.put(url)

    # Sends message to server and returns received data
    def send_request(self, msg):
        self.messages += 1
        context = ssl.create_default_context()
        context.verify_mode = ssl.CERT_REQUIRED
        context.load_default_certs()
        mysocket = context.wrap_socket(socket.socket(socket.AF_INET, socket.SOCK_STREAM), server_hostname=self.server)
        mysocket.connect((self.server, self.port))
        mysocket.send(msg.encode('ascii'))
        data = ""
        new = mysocket.recv(4096).decode('ascii')
        while new != "":
            data += new
            new = mysocket.recv(4096).decode('ascii')
        mysocket.close()
        return data

    # Runs crawler
    def run(self):

        self.login_sequence()
        self.crawl()


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='crawl Fakebook')
    parser.add_argument('-s', dest="server", type=str, default=DEFAULT_SERVER, help="The server to crawl")
    parser.add_argument('-p', dest="port", type=int, default=DEFAULT_PORT, help="The port to use")
    parser.add_argument('username', type=str, help="The username to use")
    parser.add_argument('password', type=str, help="The password to use")
    args = parser.parse_args()
    sender = Crawler(args)
    sender.run()
