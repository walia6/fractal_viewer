# Fractal Viewer

## Overview

Fractal Viewer is a robust and scalable desktop application, designed to visualize and interact with various types of fractals. Developed using Java and JavaFX, the project leverages Object-Oriented Programming (OOP) principles and the Model-View-Controller (MVC) architectural pattern. The software promotes code reusability, extensibility, and maintainability.

## Features

- **Modular Design**: Utilizes MVC design pattern for a clean separation of concerns.
- **Interactivity**: Real-time viewport adjustments for zooming and panning.
- **Optimization**: Efficient fractal generation algorithms for real-time rendering.
- **User Experience**: Intuitive UI controls for fractal selection and parameter tuning.
- **Multi-threading**: Uses concurrent programming for responsive UI experience.
  
## Software Design Patterns and Paradigms

- **Model-View-Controller (MVC)**: Enables low coupling and high cohesion for easier debugging and testing.
- **Object-Oriented Programming (OOP)**: Leverages encapsulation, inheritance, and polymorphism for reusable and maintainable code.
- **Single Responsibility Principle**: Each class is designed to handle a specific functionality.
- **Observer Pattern**: For real-time UI updates.
  
## Prerequisites

- Java 8 or higher
- JavaFX 11 or higher
- Maven

## Getting Started

### Installation

1. **Clone the Repository**
    ```shell
    git clone https://github.com/yourusername/fractal_viewer.git
    ```

2. **Navigate to Project Directory**
    ```shell
    cd fractal_viewer
    ```

3. **Build the Project with Maven**
    ```shell
    mvn clean install
    ```

4. **Run the Application**
    ```shell
    mvn exec:java
    ```

## Usage

- **Launch the application**: Double-click the executable or run the command line interface.
- **Adjust Iterations**: Use the slider to increase or decrease the number of iterations for generating the fractal.
- **Viewport Navigation**: Click and drag to move the viewport. Scroll to zoom in and out.

## Contributing

Contributions, issues, and feature requests are welcome. Feel free to fork the repository and create pull requests for your contributions.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.
