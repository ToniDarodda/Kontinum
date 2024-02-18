import styled from "styled-components";


export const ContainerNavBar = styled.div`
    height: 100vh;
    width: 400px;
    margin: 8px;

    @media (max-width: 1040px) {
        display: none;
    }
`;
export const KontinumNavbar = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: calc(100% - 16px);
    background-color: #21303E;
    border-radius: 8px;
    
    @media (max-width: 1040px) {
        display: none;
    }
`;

export const ContainerMobileNavbar = styled.div`
    display: flex;
    width: 100%;
    height: 60px;
    @media (min-width: 1040px) {
        display: none;
    }
`;

export const KontinumMobileNavbar = styled.div`
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 100%;
    background-color: #21303E;
    margin: 8px;
    border-radius: 8px;
    
    @media (min-width: 1040px) {
        display: none;
    }
`;