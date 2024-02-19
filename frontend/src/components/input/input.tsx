import React from "react";

import { ContainerInput, InputLabel, KontinumInput } from "./input.style";

interface InputInterface {
  placeholder: string;
  inputLabel: string;
  type?: string;
}

export const Input = React.forwardRef<HTMLInputElement, InputInterface>(
  ({ placeholder, inputLabel, type, ...rest }, ref) => {
    return (
      <ContainerInput>
        <InputLabel>{inputLabel}</InputLabel>
        <KontinumInput
          ref={ref}
          placeholder={placeholder}
          {...rest}
          type={type}
        />
      </ContainerInput>
    );
  },
);
