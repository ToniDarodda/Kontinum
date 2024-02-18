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


export const KontinumDiv = styled.div<{margin?: string}>`
    display: flex;
    flex-direction: row;
    height: auto;
    min-height: 100vh;
    width: calc(100% - 410px);
    margin-left: 410px;
`;

export const KontinumRegisterDiv = styled.div<{margin?: string}>`
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

export const MultiSubContainer = styled.div<{ padding?: string, border?: string, justifyContent?: string, alignItems?: string, pl?: string, w?: string, margin?: string, fd?: string, mr?: string, gap?: string}>`
    display: flex;
    flex-direction: ${props => props.fd ? props.fd : 'column'};
    width: ${props => props.w ? props.w : ''};
    align-items: ${(props) => props.alignItems ? props.alignItems : 'center'};
    justify-content: ${(props) => props.justifyContent ? props.justifyContent : 'center' };
    padding-top: ${(props) => props.padding ? props.padding : '0px'};
    padding-left: ${(props) => props.pl ? props.pl : '0px'};
    border-bottom: ${(props) => props.border ? props.border : ''};
    margin: ${props => props.margin ? props.margin : '0'};
    margin-right: ${props => props.mr ? props.mr : ''};
    gap: ${props => props.gap ? props.gap : 'auto'}
`