import styled from "styled-components";

const Title = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #BF4F74;
`;

const Wrapper = styled.section`
  padding: 4em;
  background: papayawhip;
`;

export default function Register() {
    return (
        <>
            <Wrapper>
                <Title>Hey it works</Title>
            </Wrapper>
        </>
    )
}