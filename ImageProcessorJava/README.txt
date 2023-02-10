model Package: All classes and interfaces that have to do with the model

    Design for Images:
        MyImage Class: Represents an image with an array of pixels
            MyPixel Class: Represents a pixel with a row, column, and RGB value in an image
                RGB Class: Represents an RGB value with a red, green, and blue component.

    functions Package: Functions that are used to run image processing operations on MyPixels
                       which are then mapped to entire MyImages
                       (All of them implement the java.util.function.Function interface)
        ModifyBrightnessFunction: modifies the brightness by a given amount
        VisualizeColorFunction: visualizes a given color in grayscale
        VisualizeIntensityFunction: visualizes the intensity in grayscale
        VisualizeLumaFunction: visualizes the luma value in grayscale
        VisualizeValueFunction: visualizes the value in grayscale
        ColorTransform: apply color transform filter (based on given array)

    ImageUtil Class: Utility class with method to read PPM files to a MyImage

    ImageProcessingModel Interface: model to load, save, and perform operations on images
        ImageProcessingModelMyImage class: implements the ImageProcessingModelInterface
                                           using MyImages to represent images and a HashMap
                                           to store references to MyImages
        GUIImageProcessingModel Interface: allows for extra features for the model when used in a
                                            GUI context such as getting an image from a hashmap,
                                            and getting histogram data from an image
            ImageProcessingModelGUI class: extends ImageProcessingModelMyImage to retain model
                                           functionality while implementing new features defined
                                           in GUIImageProcessingModel interface.
       **Note creating a model interface for GUI and a corresponding class was a necessary design decision
       in order to expose new features to a model that are only necessary for a GUI and not for text based
       models. This also allows us to have a model for GUI's with only access to necessary methods for
       getting relevant data for the view.


view Package: All classes and interfaces that have to do with the view

    ImageProcessingView interface: Used to render messages to the user in some way
        ImageProcessingTextView class: Renders the messages to an Appendable, used for textUI

        ImageProcessingGUIView interface: extends ImageProcessingView: Used to define actions a gui
                                                                       view can perform
            ImageProcessingGUIViewImpl class: Sets up a GUI with necessary operations, buttons, panels,
                                                image view, and histogram for funcitonality of model


controller Package: All classes and interfaces that have to do with the controller

    ImageProcessingController interface: Allows input to be passed to a model, be processed, and passed back through to a view.
            ImageProcessingControllerImpl class: Specific controller implementation using Command Design Pattern,
                                                 given ImageProcessingModel, ImageProcessingView, and a Readable to get input from
    Features interface: Represents the features of a GUI controller which are called by the view, get information from
                        the model, and return the appropriate response to the view.
            ControllerGUI: Represents a controller for handling interactions between a gui model and a gui view.
            ** Note new controller interface and controller for GUI specifically were necessary
            because the controller for a GUI operates completely differently since actions happen
            in response to user interactions in the view. This makes one execute method in the controller
            no longer adequate.



    commands Package: Contains all the commands the user can input to the controller


ImageProcessor class: contains the main method used to run the TextUI

Histogram class: with given histogram data, can draw itself as a jpanel to display histogram data
                 overlayed as a bar chart for red, green, blue, and intensity values

Image Used for Starting in GUI:
https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Koe_in_weiland_bij_Gorssel.JPG/1200px-Koe_in_weiland_bij_Gorssel.JPG


Extra credit Down Scale Implementation:

Nothing, design wise was needed to implement the scale feature.
Implementing this new feature was the same as implementing a normal MyImage method which
does the actual downscaling. Then we needed to add a button in the view that corresponds to this
operation and calls a method in the controller. This controller method will call the model's method
that calls the MyImage downscale method and adds to its hashmap.








