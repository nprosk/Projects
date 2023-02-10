Our initial approach to the crawler was to
make an HTTP 1.1 client with encrypted connection that
could go to the login page and get basic information. After
retrieving basic information from the login page we used POST
to fill out the login form and get the new cookies for our session.
We then came up with a basic idea for how our Crawler would
explore Fakebook using DFS. The idea was a while loop that kept
a queue of unexplored URls and would go through them using
an html parser and mark them as visited while adding any URls,
not visited, on that page to the queue. 
Then we added functionality to look for secret flags using
the same html parser. The final touches were to add support
for the htpp 302 and 503 error messages. No extra work
was required to implement handling of 400 error messages.

Some challenges we faced were getting the POST message to 
work because we didn't know what was required to send
to the server in the message. Originally, we sent much
less information than was required causing cookie errors.
Another challenge was making sure that we were not visiting
duplicate URL's because we checked if they were visited
before adding them to the unexplored queue. But, they
were only marked visited if they had been explored, so multiple
identical URLs were being added to the unexplored queue.

We tested our code by running our crawler on the server
using both of our logins to retrieve the flags. 