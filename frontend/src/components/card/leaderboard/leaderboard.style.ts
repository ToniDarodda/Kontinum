import styled from "styled-components";

export const LeaderboardInfo = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: column;
  height: 150px;
  width: 150px;

  margin: 12px;

  border-radius: 8px;
  justify-content: center;
  align-items: center;
  background-color: ${(props) => props.backgroundColor};
  border: 1px solid #efeeee;
`;
