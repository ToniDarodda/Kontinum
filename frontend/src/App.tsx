import React from "react";

import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import { ThemeProvider } from "styled-components";

import { GlobalStyles } from "./global/global.style";
import {
  Dashboard,
  Register,
  Login,
  Users,
  Stock,
  Cocktail,
  Leaderboard,
  Discount,
  Profile,
} from "./pages";

const theme = {
  color: "#ffffff",
};

const router = createBrowserRouter([
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/dashboard",
    element: <Dashboard />,
  },
  {
    path: "/users",
    element: <Users />,
  },
  {
    path: "/stock",
    element: <Stock />,
  },
  {
    path: "/cocktail",
    element: <Cocktail />,
  },
  {
    path: "/leaderboard",
    element: <Leaderboard />,
  },
  {
    path: "/discount",
    element: <Discount />,
  },
  {
    path: "/profile",
    element: <Profile />,
  },
]);

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: Infinity,
    },
  },
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
  );
}

export default App;
