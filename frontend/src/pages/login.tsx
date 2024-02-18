import React from "react";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { toast } from "react-toastify";

import { ContainerRegister, KontinumRegisterDiv, MultiSubContainer } from "../global/global.style";
import { useLoginBusiness } from "../query";
import { KontinumP, KontinumSignature, KontinumTitleH1, KontinumTitleH2 } from "../components/title/title.style";
import { KontinumForm } from "../components/form/form";
import { Input } from "../components/input/input";
import { KontinumButton } from "../components/button/button.style";
import { Image } from "../components";

type Inputs = {
    mail: string;
    password: string;
}
export function Login(): React.ReactElement {
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Inputs>()

    const { mutate: login} = useLoginBusiness()

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        login({
            ...data,
        }, {
            onSuccess: () => navigate('/dashboard'),
            onError: (error) => {
                console.log(error);
            }
        })
    }

    const redirectRegister = () => navigate('/register')


    return (
        <KontinumRegisterDiv>
            <Image />
            <ContainerRegister>
                <MultiSubContainer padding={'50px'}>
                    <KontinumTitleH2 opacity={0.6}>Welcome to</KontinumTitleH2>
                    <KontinumTitleH1>Kontinum</KontinumTitleH1>
                    <KontinumP opacity={0.6} margin={'0px 0px 20px 0px'}>Create your account to browse the site.</KontinumP>
                </MultiSubContainer>

                <KontinumForm onSubmit={handleSubmit(onSubmit)}>
                    <Input placeholder={'Enter your business email....'} inputLabel={'Business email'} {...register("mail", { required: true })} ></Input>
                    {errors.mail && <span>This field is required</span>}
                    <Input placeholder={'Enter your password....'} inputLabel={'Password'} {...register("password", { required: true })} ></Input>
                    {errors.password && <span>This field is required</span>}
                    <KontinumP cursor={'pointer'} opacity={0.6} margin={'20px 0px 32px 0px'}>Forgot your password?</KontinumP>

                    <MultiSubContainer padding={'20px'}>
                        <KontinumButton type={'submit'}>Login</KontinumButton>
                    </MultiSubContainer>
                    <KontinumP cursor={'pointer'} opacity={0.6} margin={'20px 0px 32px 0px'} onClick={redirectRegister}>You don't have an account yet?</KontinumP>

                    <MultiSubContainer padding={'80px'}>
                        <KontinumSignature opacity={0.6}>Copyright by Kontinum 2024. All Rights Reserved.</KontinumSignature>
                    </MultiSubContainer>

                </KontinumForm>
            </ContainerRegister>
        </KontinumRegisterDiv>
    )
}
