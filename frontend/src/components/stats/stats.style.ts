import styled from "styled-components";

export const ContainerStatsUser = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    width: 360px;
    height: 30%;
    margin: 8px 8px 4px;
    border-radius: 8px;
    background-color: #FCFBFB;
    border: 1px solid #ECEBEB;

`;

export const ContainerStatsPurchase = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;

    width: 360px;
    height: 70%;
    margin: 4px 8px 8px;

    border-radius: 8px;
    background-color: #FCFBFB;
    border: 1px solid #ECEBEB;
`;

export const ButtonAddUser = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    
    width: 200px;
    height: 40px;
    border-radius: 12px;
    background-color: #21303E;
    color: #ffffff;
    box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0;
    cursor: pointer;
    
    &:hover {
        background-color: #314251;
    }
`;