import React from "react";

import { Card } from "../components/card/card";
import { Info } from "../components/card/info/info";
import { Leaderboard } from "../components/card/leaderboard/leaderboard";
import Navbar from "../components/navbar/navbar";
import { NavigationButtonOption } from "../components/navbar/navigation/navigation";
import { Stats } from "../components/stats/stats";
import { KontinumP } from "../components/title/title.style";
import {
  DashboardDiv,
  KontinumDiv,
  MultiSubContainer,
} from "../global/global.style";

export function Dashboard(): React.ReactElement {
  return (
    <>
      <Navbar defaultOption={NavigationButtonOption.DASHBOARD} />
      <KontinumDiv>
        <DashboardDiv>
          <MultiSubContainer
            justifyContent={"space-between"}
            margin={"8px"}
            alignItems={"normal"}
          >
            <MultiSubContainer
              fd={"row"}
              justifyContent={"flex-start"}
              margin={"20px 20px 0"}
            >
              <MultiSubContainer alignItems={"flex-start"} margin={"0 0 60px"}>
                <KontinumP>Dashboard</KontinumP>
                <KontinumP opacity={0.6}>Latest updates</KontinumP>
              </MultiSubContainer>
            </MultiSubContainer>
            <Card />
          </MultiSubContainer>
          <MultiSubContainer margin={"8px"}>
            <Info></Info>
          </MultiSubContainer>
          <MultiSubContainer margin={"8px"}>
            <Leaderboard></Leaderboard>
          </MultiSubContainer>
        </DashboardDiv>
        <Stats />
      </KontinumDiv>
    </>
  );
}
