import styled from "styled-components";

export const InfoCard = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: auto;

  min-height: 230px;

  justify-content: flex-start;
  align-items: center;
  background-color: #fafafa;
  border-radius: 8px;
  box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0;
`;

export const DrinkImageContainer = styled.div`
  height: 50px;
  width: 50px;
  background-image: url("/coco.png");
  background-repeat: no-repeat;
  object-fit: fill;
`;

export const DrinkInfo = styled.div`
  display: flex;
  flex-direction: column;
  height: 130px;
  width: 130px;

  margin: 12px;

  border-radius: 8px;
  justify-content: flex-start;
  align-items: center;
  background-color: #f1f1f1;
`;
