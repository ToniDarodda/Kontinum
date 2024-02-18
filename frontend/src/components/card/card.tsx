import {KontinumCard, Wrapper} from "./card.style";
import {MultiSubContainer} from "../../global/global.style";
import {KontinumP} from "../title/title.style";
import {useState} from "react";

export function Card() {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [cocktailsSale, setCocktailsSale] = useState<string>('0');
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [sales, setSales] = useState<string>('$ 0')
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const [newUser, setNewUser] = useState<string>('0')

    return (
        <Wrapper justifyContent={'space-between'} fd={'row'} margin={'20px'}>

            <KontinumCard color={'#F8FCFF'}>
                <MultiSubContainer margin={'8px'}>
                    Number of cocktails
                </MultiSubContainer>
                <MultiSubContainer margin={'30px 0 0'}>
                    <KontinumP fs={'40px'} color={'black'}>{cocktailsSale}</KontinumP>
                </MultiSubContainer>
            </KontinumCard>
            <KontinumCard color={'#EBF5FF'}>
                <MultiSubContainer margin={'8px'}>
                    Sales
                </MultiSubContainer>
                <MultiSubContainer margin={'30px 0 0'}>
                    <KontinumP fs={'40px'} color={'black'}>{sales}</KontinumP>
                </MultiSubContainer>
            </KontinumCard>
            <KontinumCard color={'#D9EEFF'}>
                <MultiSubContainer margin={'8px'}>
                    New users this month
                </MultiSubContainer>
                <MultiSubContainer margin={'30px 0 0'}>
                    <KontinumP fs={'40px'} color={'black'}>{newUser}</KontinumP>
                </MultiSubContainer>
            </KontinumCard>
        </Wrapper>
    )
}