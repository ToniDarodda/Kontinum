import { useState } from "react";

import { LeaderboardInfo } from "./leaderboard.style";
import { MultiSubContainer } from "../../../global/global.style";
import { UserType } from "../../../interfaces";
import { KontinumP } from "../../title/title.style";
import { Wrapper } from "../card.style";
import { InfoCard } from "../info/info.style";

export function Leaderboard() {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [leaderboard, setLeaderboard] = useState<UserType[]>([
    {
      id: "1",
      firstName: "Alexandre",
      lastName: "Garage",
      email: "d",
      businessId: 1,
    },
    {
      id: "1",
      firstName: "Alexandre",
      lastName: "Garage",
      email: "d",
      businessId: 1,
    },
    {
      id: "1",
      firstName: "Alexandre",
      lastName: "Garage",
      email: "d",
      businessId: 1,
    },
  ]);

  const applyColor = (i: number) => {
    if (i === 0) return "#F8FCFF";
    else if (i === 1) return "#EBF5FF";
    else return "#D9EEFF";
  };

  return (
    <InfoCard>
      <MultiSubContainer margin={"8px"}>
        <KontinumP color={"black"} fs={"20px"} opacity={0.6}>
          Leaderboard
        </KontinumP>
      </MultiSubContainer>
      <Wrapper fd={"row"} justifyContent={"space-between"} w={"100%"}>
        {leaderboard.map((s, index) => {
          return (
            <LeaderboardInfo key={s.id} backgroundColor={applyColor(index)}>
              <MultiSubContainer margin={"30px"} justifyContent={"center"}>
                <KontinumP>
                  {s.firstName} {s.lastName}
                </KontinumP>
              </MultiSubContainer>
            </LeaderboardInfo>
          );
        })}
      </Wrapper>
    </InfoCard>
  );
}
