import styled from "styled-components";
import React from "react";

const Title = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #BF4F74;
`;

const Wrapper = styled.section`
  padding: 4em;
  background: papayawhip;
`;

export function Dashboard(): React.ReactElement {
    return (
            <Wrapper>
                <Title>Hey it works</Title>
            </Wrapper>
    )
}