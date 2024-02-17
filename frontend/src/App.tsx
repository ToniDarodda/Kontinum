import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { ThemeProvider } from "styled-components";

import { Dashboard, Register, Login } from "./pages";
import { GlobalStyles } from "./global/global.style";
import {ToastContainer} from "react-toastify";

const theme = {
    color: "#ffffff"
}

const router = createBrowserRouter([
    {
        path: "/register",
        element: <Register />,
    },
    {
        path: '/login',
        element: <Login />
    },
    {
        path: "/dashboard",
        element: <Dashboard />
    }
]);

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: Infinity
        }
    }
});

function App(): React.ReactElement {
  return (
      <ThemeProvider theme={theme}>
          <QueryClientProvider client={queryClient}>
              <GlobalStyles />
              <ToastContainer />
              <RouterProvider router={router} />
              <ReactQueryDevtools initialIsOpen={true} />
          </QueryClientProvider>
      </ThemeProvider>
  )
}

export default App;
