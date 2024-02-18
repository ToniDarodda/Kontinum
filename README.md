# Kontinum

Kontinum is a full-stack application designed to streamline bar management, offering an intuitive interface for managing inventory, orders, and customer interactions. The backend is built in Kotlin using the Ktor framework for a high-performance and reactive architecture. The frontend, still in its draft phase, is developed with React to ensure a smooth and modern user experience. Yarn is used as the package manager for the frontend.

## Installation

Follow these instructions to install and run Kontinum on your system.

### Prerequisites

- JDK (Java Development Kit) for Kotlin
- Gradle for building the backend
- Node.js and Yarn for the frontend

### Backend Setup

1. Clone the repository and navigate to the backend directory:
   ```bash
   git clone https://example.com/kontinum.git
   cd kontinum/backend
   ```

2. Build and run the backend server:
   ```bash
   gradle build
   gradle run
   ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```

2. Install dependencies with Yarn:
   ```bash
   yarn install
   ```

3. Start the frontend application:
   ```bash
   yarn start
   ```

The application will be accessible at `http://localhost:3000`.

## Design

For a detailed look at the design aspects of Kontinum, check out our [Figma design file](https://www.figma.com/file/Q0DCDkYKprRKvmE4sW8nPJ/Kontinum?type=design&node-id=52%3A942&mode=design&t=WLhf4qDbIzP3CdRz-1).

## Database Model

To view the database model for Kontinum, visit our [Lucidchart diagram](https://lucid.app/lucidchart/bbcf10f0-ef71-4022-b5a7-eab1e8fb6033/edit?viewport_loc=-741%2C-54%2C3164%2C2033%2C0_0&invitationId=inv_5d063bf4-c444-418a-95e5-4f00340125ac).

## Features

- **Inventory Management**: Real-time tracking of beverage stocks.
- **Orders**: Simplified handling of customer orders.
- **User Interface**: Clean and intuitive interface.

> **Note:** The project is under development. The backend is functional, but the frontend is still in the draft phase, with only a few pages designed so far.

## Contributing

Contributions are welcome to help complete the frontend and enhance backend functionalities. To contribute:

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some amazing features'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## License

This project is distributed under the MIT License. See the `LICENSE` file for more information.

---
