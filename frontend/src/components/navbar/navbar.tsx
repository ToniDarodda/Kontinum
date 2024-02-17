import {ContainerNavBar, KontinumNavbar} from './navbar.style';
import {KontinumP, KontinumTitleH3} from "../title/title.style";
import {MultiSubContainer} from "../../global/global.style";
import {NavigationButtonOption, NavigationManager} from "./navigation/navigation";

interface NavbarInterface {
    defaultOption: NavigationButtonOption;
}
export default function Navbar({ defaultOption }: NavbarInterface) {
    return (
        <ContainerNavBar>
            <KontinumNavbar>
                <MultiSubContainer padding={'30px'}>
                    <KontinumTitleH3 color={'#ffffff'}>Kontinum</KontinumTitleH3>
                </MultiSubContainer>
                <KontinumP color={'#ffffff'} opacity={0.4}>Welcome to your dashboard</KontinumP>
                <MultiSubContainer padding={'60px'} border={'1px solid #ffffff'} />
                <MultiSubContainer justifyContent={'center'} alignItems={'flex-start'} pl={'12px'} padding={'10px'}>
                    <KontinumP margin={'8px'} color={'#ffffff'} opacity={1} fs={'18px'}>Functionality</KontinumP>
                </MultiSubContainer>
                <NavigationManager defaultOption={defaultOption}/>

            </KontinumNavbar>
        </ContainerNavBar>
    )
}