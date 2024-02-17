import React from "react";
import Navbar from "../components/navbar/navbar";
import {NavigationButtonOption} from "../components/navbar/navigation/navigation";

export function Users(): React.ReactElement {
    return (
        <Navbar defaultOption={NavigationButtonOption.USERS}/>
    )
}