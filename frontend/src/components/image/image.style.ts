import styled from "styled-components";

export const LeftContainerWelcomeImage = styled.div`
    height: 100%;
    width: 800px;
    margin: 8px;
    
    @media (max-width: 1250px) {
        display: none;
    }
`

export const LeftWelcomeImage = styled.div`
    height: calc(100% - 16px);
    width: 100%;
    background-image: url('/kontinum.png');
    background-repeat: no-repeat;
    object-fit: fill;
    border-radius: 8px;
`
