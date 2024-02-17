import styled from "styled-components";

interface TextPropsInterface {
    opacity?: number;
    cursor?: string;
    margin?: string;
}

export const KontinumTitleH1 = styled.h1<TextPropsInterface>`
    font-size: 90px;
    font-weight: bolder;
    color: #0E4477;
    margin: 4px;
    opacity: ${props => props.opacity ? props.opacity : 1};
`;
export const KontinumTitleH2 = styled.h2<TextPropsInterface>`
    font-size: 60px;
    font-weight: lighter;
    color: #0E4477;
    margin: 4px;
    opacity: ${props => props.opacity ? props.opacity : 1};
`;
export const KontinumTitleH3 = styled.h3<TextPropsInterface>`
    opacity: ${props => props.opacity ? props.opacity : 1};

`;
export const KontinumTitleH4 = styled.h4<TextPropsInterface>`
    opacity: ${props => props.opacity ? props.opacity : 1};
`;

export const KontinumP = styled.p<TextPropsInterface>`
    font-size: 16px;
    font-weight: normal;
    text-align: center;
    color: #0E4477;
    opacity: ${props => props.opacity ? props.opacity : 1};
    margin: ${props => props.margin ? props.margin : '0px'};
    cursor: ${props => props.cursor ? props.cursor : 'auto'};
`;

export const KontinumSignature = styled.p<TextPropsInterface>`
    font-size: 14px;
    font-weight: normal;
    color: #0E4477;
    opacity: ${props => props.opacity ? props.opacity : 1};
    margin: ${props => props.margin ? props.margin : '0px'};
    text-align: center;
    position: fixed;
`;