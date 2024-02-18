import React from "react";
import styled from "styled-components";

import Navbar from "../components/navbar/navbar";
import {NavigationButtonOption} from "../components/navbar/navigation/navigation";
import {Card} from "../components/card/card";
import {KontinumDiv, MultiSubContainer} from "../global/global.style";
import {KontinumP} from "../components/title/title.style";
import {Stats} from "../components/stats/stats";
import {Info} from "../components/card/info/info";
import {Leaderboard} from "../components/card/leaderboard/leaderboard";

const Div = styled.div`
    display: flex;
    flex-direction: column;
    flex: 1;
    background-color: #FCFBFB;
    margin: 8px 4px;
    border-radius: 8px;
    border: 1px solid #ECEBEB;
`;

export function Dashboard(): React.ReactElement {
    return (
        <>
        <Navbar defaultOption={NavigationButtonOption.DASHBOARD}/>
        <KontinumDiv>
            <Div>
                <MultiSubContainer justifyContent={'space-between'} margin={'8px'} alignItems={'normal'}>
                    <MultiSubContainer fd={'row'} justifyContent={'flex-start'} margin={'20px 20px 0'}>
                        <MultiSubContainer alignItems={'flex-start'} margin={'0 0 60px'}>
                            <KontinumP>Dashboard</KontinumP>
                            <KontinumP opacity={0.6}>Latest updates</KontinumP>
                        </MultiSubContainer>
                    </MultiSubContainer>
                    <Card />
                </MultiSubContainer>
                <MultiSubContainer margin={'8px'}>
                    <Info></Info>
                </MultiSubContainer>
                <MultiSubContainer margin={'8px'}>
                    <Leaderboard></Leaderboard>
                </MultiSubContainer>
            </Div>
            <Stats />
        </KontinumDiv>
        </>
    )
}
