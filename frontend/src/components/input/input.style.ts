import styled from "styled-components";


export const ContainerInput = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    width: 450px;
    height: auto;
    
    @media (max-width: 600px) {
        width: 90%;
    }
`

export const InputLabel = styled.p`
    font-size: 16px;
    font-weight: normal;
    color: #0E4477;
    padding-left: 4px;
`
export const KontinumInput = styled.input`
    width: 100%;
    height: 36px;
    border-radius: 20px;
    padding: 12px;
    border: 1px solid #0E4477;
    color: #0E4477;
    font-size: 16px;
    opacity: 0.8;
    
    ::placeholder {
        color: #0E4477;
        opacity: 1;
    }
    
    &:focus {
        outline: 1px solid #0E4477;
    }
`