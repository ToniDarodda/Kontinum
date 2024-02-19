import { useState } from "react";

import { useNavigate } from "react-router-dom";

import { NavigationButton, NavigationButtonLogOut } from "./navigation.style";
import { MultiSubContainer } from "../../../global/global.style";
import { KontinumP } from "../../title/title.style";

export enum NavigationButtonOption {
  DASHBOARD = "dashboard",
  USERS = "users",
  STOCK = "stock",
  COCKTAIL = "cocktail",
  DISCOUNT = "discount",
  LEADERBOARD = "leaderboard",
  PROFILE = "profile",
  LOGOUT = "logout",
}

interface NavigationManagerInterface {
  defaultOption: NavigationButtonOption;
}
export function NavigationManager({
  defaultOption,
}: NavigationManagerInterface) {
  const navigate = useNavigate();
  const [buttonOption, setButtonOption] =
    useState<NavigationButtonOption>(defaultOption);

  const isOptionActive = (option: NavigationButtonOption): boolean => {
    return option === buttonOption;
  };

  const optionChange = (option: NavigationButtonOption): void => {
    setButtonOption(option);
    navigate(`/${option.toString()}`);
  };

  return (
    <MultiSubContainer>
      <MultiSubContainer>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.DASHBOARD)}
          onClick={() => optionChange(NavigationButtonOption.DASHBOARD)}
        >
          Dashboard
        </NavigationButton>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.USERS)}
          onClick={() => optionChange(NavigationButtonOption.USERS)}
        >
          Users
        </NavigationButton>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.STOCK)}
          onClick={() => optionChange(NavigationButtonOption.STOCK)}
        >
          Stock
        </NavigationButton>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.COCKTAIL)}
          onClick={() => optionChange(NavigationButtonOption.COCKTAIL)}
        >
          Cocktail
        </NavigationButton>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.DISCOUNT)}
          onClick={() => optionChange(NavigationButtonOption.DISCOUNT)}
        >
          Discount
        </NavigationButton>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.LEADERBOARD)}
          onClick={() => optionChange(NavigationButtonOption.LEADERBOARD)}
        >
          Leaderboard
        </NavigationButton>
      </MultiSubContainer>
      <MultiSubContainer
        w={"100%"}
        padding={"60px"}
        border={"1px solid #ffffff"}
      />
      <MultiSubContainer
        justifyContent={"center"}
        alignItems={"flex-start"}
        padding={"10px"}
      >
        <KontinumP margin={"8px"} color={"#ffffff"} opacity={1} fs={"18px"}>
          Settings
        </KontinumP>
        <NavigationButton
          isActive={isOptionActive(NavigationButtonOption.PROFILE)}
          onClick={() => optionChange(NavigationButtonOption.PROFILE)}
        >
          Profile
        </NavigationButton>
        <NavigationButtonLogOut
          isActive={false}
          onClick={() => navigate("/login")}
        >
          Log out
        </NavigationButtonLogOut>
      </MultiSubContainer>
    </MultiSubContainer>
  );
}
