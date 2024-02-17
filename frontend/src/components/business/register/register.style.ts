import styled from "styled-components";

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