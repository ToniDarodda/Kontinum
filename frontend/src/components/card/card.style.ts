import styled from "styled-components";

export const KontinumCard = styled.div<{ color: string }>`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  height: 200px;
  width: 200px;
  border-radius: 8px;
  margin-bottom: 4px;
  border: 1px solid #cbcaca;
  box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0;
  background-color: ${(props) => props.color};
`;

export const Wrapper = styled.div<{
  padding?: string;
  border?: string;
  justifyContent?: string;
  alignItems?: string;
  pl?: string;
  w?: string;
  margin?: string;
  fd?: string;
}>`
  display: flex;
  flex-direction: ${(props) => (props.fd ? props.fd : "column")};
  width: ${(props) => (props.w ? props.w : "")};
  align-items: ${(props) => (props.alignItems ? props.alignItems : "center")};
  justify-content: ${(props) =>
    props.justifyContent ? props.justifyContent : "center"};
  padding-top: ${(props) => (props.padding ? props.padding : "0px")};
  padding-left: ${(props) => (props.pl ? props.pl : "0px")};
  border-bottom: ${(props) => (props.border ? props.border : "")};
  margin: ${(props) => (props.margin ? props.margin : "0")};
  flex-wrap: wrap;
`;
