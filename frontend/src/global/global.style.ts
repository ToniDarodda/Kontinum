import styled, { createGlobalStyle } from 'styled-components';

export const GlobalStyles = createGlobalStyle`
    @import url('https://fonts.googleapis.com/css2?family=Merienda&display=swap');

    body {
        font-family: 'Merienda', cursive;
    }
    
    button {
        font-family: 'Merienda', cursive;
        cursor: pointer;
    }

    input::placeholder {
        color: #0E4477;
        opacity: 0.5;
        font-family: 'Merienda', cursive;
    }

`;


export const KontinumDiv = styled.div`
    display: flex;
    height: 100%;
    width: 100%;
    flex-direction: row;
`;

export const ContainerRegister = styled.div`
    display: flex;
    flex: 1;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
`

export const MultiSubContainer = styled.div<{ padding?: string }>`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    
    padding-top: ${(props) => props.padding ? props.padding : '0px'};
`