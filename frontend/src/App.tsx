import React from 'react';

import { Register } from "./pages";

import { ThemeProvider } from "styled-components";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

const theme = {
    color: "#ffffff"
}

const router = createBrowserRouter([
    {
        path: "/register",
        element: <Register />,
    },
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
              <RouterProvider router={router} />
              <ReactQueryDevtools initialIsOpen={true} />
          </QueryClientProvider>
      </ThemeProvider>
  )
}

export default App;
