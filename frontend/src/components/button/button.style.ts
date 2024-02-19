import styled from "styled-components";

export const KontinumButton = styled.button`
  width: 450px;
  height: 60px;
  border-radius: 12px;
  border: none;
  background-color: #0e4477;
  color: #ffffff;
  font-size: 20px;

  &:hover {
    background-color: #0e4477;
    opacity: 0.8;
    border: 1px solid #64b5f6;
  }

  @media (max-width: 600px) {
    width: 90%;
  }
`;
