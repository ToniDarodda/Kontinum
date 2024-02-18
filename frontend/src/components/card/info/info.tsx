import {DrinkImageContainer, DrinkInfo, InfoCard} from "./info.style";
import { KontinumP } from "../title/title.style";
import { MultiSubContainer } from "../../global/global.style";
import {Wrapper} from "./card.style";
import {useState} from "react";

export function Info() {

    const [stock, setStock] = useState([
        {
            id: '1',
            drinkName: 'Rum',
            capacity: 204,
            cocktailId: '1'
        },
        {
            id: '1',
            drinkName: 'Bear',
            capacity: 20,
            cocktailId: '1'
        },
        {
            id: '1',
            drinkName: 'Wine',
            capacity: 145,
            cocktailId: '1'
        },
        {
            id: '1',
            drinkName: 'Vodka',
            capacity: 2,
            cocktailId: '1'
        },
    ])

    return (
        <InfoCard>
            <MultiSubContainer margin={'20px'}>
                <KontinumP color={'black'} fs={'20px'} opacity={0.6}>Stock info</KontinumP>
            </MultiSubContainer>
                <Wrapper fd={'row'} justifyContent={'space-between'} w={'100%'}>
                    {stock.map((s) => {
                        return (
                            <DrinkInfo>
                                <MultiSubContainer margin={'8px'}>
                                    <KontinumP>{s.drinkName}</KontinumP>
                                </MultiSubContainer>
                                <MultiSubContainer>
                                    <DrinkImageContainer />
                                </MultiSubContainer>
                                <MultiSubContainer margin={'10px'}>
                                    <KontinumP>{s.capacity}</KontinumP>
                                </MultiSubContainer>
                            </DrinkInfo>
                        )
                    })}
                </Wrapper>
        </InfoCard>
    )
}