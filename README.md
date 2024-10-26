# Sprint Reflections Form

## Overview
The Sprint Reflections Form is a JavaFX application designed to help users document and reflect on their project sprints. The application provides input fields for the project name, sprint number, and allows users to record what went well, the challenges faced, and areas for improvement for each sprint. Feedback can be saved to a text file, making it easy to track and review reflections across sprints.

## Features
- **Input Fields**: Text fields for project name and sprint number.
- **Text Areas**: Areas to record what went well, challenges faced, and areas for improvement.
- **File Saving**: Save reflections to a `.txt` file with a specified format (`%project name%-%sprint number%-%date%.txt`).
- **User-Friendly Navigation**: Support for easy tab traversal between text areas for a smooth user experience.

## Prerequisites
- Java 11 or higher.
- JavaFX SDK (JavaFX is not included in Java from version 11 onwards).
- Maven (for building and running the project).

## Setup and Run Instructions
1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd sprint-reflections-form
   ```

2. **Add JavaFX to Classpath**
   Ensure that JavaFX is properly set up on your system. You can add it via Maven by including the following dependency in your `pom.xml` file:
   ```xml
   <dependency>
       <groupId>org.openjfx</groupId>
       <artifactId>javafx-controls</artifactId>
       <version>20</version>
   </dependency>
   ```

3. **Build and Run the Application**
    - To build the project and create a JAR file:
      ```sh
      mvn clean package
      ```
    - To run the application:
      ```sh
      java --module-path /path/to/javafx-sdk-20/lib --add-modules javafx.controls,javafx.fxml -jar target/sprint-reflections-form.jar
      ```
   Replace `/path/to/javafx-sdk-20/lib` with the correct path to your JavaFX SDK.

## Usage
1. **Enter Details**: Provide the project name and sprint number.
2. **Fill Out Reflection Areas**: Document what went well, challenges faced, and potential improvements.
3. **Save Feedback**: Click the "Save Feedback" button to save your reflections to a text file.

## File Naming Format
The application saves feedback with the following naming convention:
```
%project name%-%sprint number%-%date%.txt
```
This ensures each sprint's reflections are uniquely saved and easy to locate.

## Notes
- Ensure that JavaFX runtime components are available when running the JAR file.
- The file chooser defaults to the user's `Dropbox/Portfolio Projects/Documentation` directory.

## Disclaimer
This application is intended for personal use, and some folder locations are specific to my setup. For example, the file chooser uses the following default directory:
```
fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Dropbox/Portfolio Projects/Documentation/Sprint Reflections"));
```
Please adjust these folder paths as necessary to suit your environment.

## License
This project is licensed under the MIT License.

## Contributions
Feel free to fork this repository and submit pull requests. Contributions are welcome!

## Contact
For any questions or feedback, please contact the project maintainer at [dusti@dustijohnson.com].

