import styled from "styled-components";

export const NavigationButton = styled.div<{ isActive: boolean }>`
    display: flex;
    flex-direction: row;
    width: 360px;
    height: 60px;
    border-radius: 8px;
    background-color: ${(props) => props.isActive ? '#ffffff' : ''};
    color: ${(props) => props.isActive ? 'black' : '#ffffff'};
    text-align: center;
    align-items: center;
    justify-content: center;
    font-size: 18px;
    margin-top: 8px;
    cursor: pointer;

    &:hover {
        background-color: ${props => props.isActive ? '#ffffff' : '#c0c0c0'};
        color: ${(props) => props.isActive ? 'black' : ''};
        opacity: ${props => props.isActive ? 1 : 1};
    }
`;

export const NavigationButtonLogOut = styled.div<{ isActive: boolean }>`
    display: flex;
    flex-direction: row;
    width: 360px;
    height: 60px;
    border-radius: 8px;
    background-color: ${(props) => props.isActive ? '#ffffff' : ''};
    color: ${(props) => props.isActive ? 'black' : '#ffffff'};
    text-align: center;
    align-items: center;
    justify-content: center;
    font-size: 18px;
    margin-top: 8px;
    cursor: pointer;
`;