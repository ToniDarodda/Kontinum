import React from "react";

import { Image } from "../components";
import { RegisterInputManager } from "../components/business/register/register";
import { KontinumDiv } from "../global/global.style";

export function Register(): React.ReactElement {
    return (
        <KontinumDiv>
            <Image />
            <RegisterInputManager />
        </KontinumDiv>
    )
}