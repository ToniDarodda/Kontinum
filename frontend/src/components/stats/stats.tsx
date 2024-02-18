import {MultiSubContainer} from "../../global/global.style";
import {ButtonAddUser, ContainerStatsPurchase, ContainerStatsUser} from "./stats.style";
import {KontinumP, KontinumTitleH2} from "../title/title.style";
import {Sale} from "../card/sale/sale";
import { useGetUsers } from "../../query";
import {UserType} from "../../interfaces";
import {useNavigate} from "react-router-dom";

export function Stats() {
    const navigate = useNavigate()
    const { data: users }: {data : UserType[] | undefined}  = useGetUsers()

    const navigateToUser = () => navigate('/users')

    return (
        <MultiSubContainer>
            <ContainerStatsUser>
                <MultiSubContainer alignItems={'flex-start'} w={'100%'} justifyContent={'flex-start'} margin={'20px'} mr={'-20px'}>
                    <KontinumP color={'black'}>Total users</KontinumP>
                    <KontinumP color={'black'}>Updated</KontinumP>
                </MultiSubContainer>
                <MultiSubContainer margin={'20px 0 0'}>
                    <KontinumTitleH2 fs={'50px'} color={'black'}>
                        {users?.length}
                    </KontinumTitleH2>
                </MultiSubContainer>
                <MultiSubContainer margin={'20px 0 0'}>
                    <ButtonAddUser onClick={navigateToUser}>Add user</ButtonAddUser>
                </MultiSubContainer>
            </ContainerStatsUser>
            <ContainerStatsPurchase>
                <MultiSubContainer alignItems={'center'} justifyContent={'flex-start'} fd={'row'} w={'100%'} margin={'20px'} gap={'160px'}>
                    <MultiSubContainer>
                        <KontinumP color={'black'}>Recent sales</KontinumP>
                    </MultiSubContainer>
                    <MultiSubContainer>
                        <KontinumP color={'black'} opacity={0.4}>See all</KontinumP>
                    </MultiSubContainer>
                </MultiSubContainer>
                <MultiSubContainer justifyContent={'center'} alignItems={'center'} w={'100%'}>
                    <Sale />
                </MultiSubContainer>
            </ContainerStatsPurchase>
        </MultiSubContainer>
    )
}