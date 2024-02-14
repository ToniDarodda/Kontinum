import React from 'react';

import { Register } from "./pages";

import { ThemeProvider } from "styled-components";

const theme = {
    color: "#ffffff"
}

function App(): JSX.Element {
  return (
      <ThemeProvider theme={theme}>
        <Register />
      </ThemeProvider>
  )
}

export default App;
