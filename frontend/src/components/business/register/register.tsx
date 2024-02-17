import 'react-toastify/dist/ReactToastify.css';
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { toast } from "react-toastify";


import { ContainerRegister, MultiSubContainer } from "./register.style";
import { KontinumP, KontinumSignature, KontinumTitleH1, KontinumTitleH2 } from "../../title/title.style";
import { KontinumButton } from "../../button/button.style";
import { Input } from "../../input/input";
import { RegisterFlow } from "../../../constants/pages/register";
import { useCreateBusiness } from "../../../query";
import { KontinumForm } from "../../form/form";

type Inputs = {
    businessName: string;
    businessLocation: string;
    businessPhoneNumber: string;
    businessEmail: string;
    password: string;
    verifyPassword: string;
}

export function RegisterInputManager() {
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Inputs>()

    const [state, setState] = useState<RegisterFlow>(RegisterFlow.NO_INFO_FILLED);

    const { mutate} = useCreateBusiness()

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        if (state === RegisterFlow.NO_INFO_FILLED) setState(RegisterFlow.FIRST_INFO_FILLED)
        else if (state === RegisterFlow.FIRST_INFO_FILLED) {
            setState(RegisterFlow.SECOND_INFO_FILLED)
            mutate({
                ...data,
                businessLegalInformation: '',
            }, {
                onSuccess: () => navigate('/dashboard'),
                onError: (error) => {
                    toast.error(error.message, {
                        position: 'top-center',
                    });
                    setState(RegisterFlow.FIRST_INFO_FILLED);
                }
            })
        }

    }


    return (
        <ContainerRegister>
            <MultiSubContainer padding={'50px'}>
                <KontinumTitleH2 opacity={0.6}>Welcome to</KontinumTitleH2>
                <KontinumTitleH1>Kontinum</KontinumTitleH1>
                <KontinumP opacity={0.6} margin={'0px 0px 20px 0px'}>Create your account to browse the site.</KontinumP>
            </MultiSubContainer>

            <KontinumForm onSubmit={handleSubmit(onSubmit)}>
                {state === RegisterFlow.NO_INFO_FILLED &&
                    <>
                        <Input placeholder={'Enter your business name....'} inputLabel={'Business name'} {...register("businessName", { required: true })} ></Input>
                        {errors.businessName && <span>This field is required</span>}
                        <Input placeholder={'Enter your business location....'} inputLabel={'Business location'} {...register("businessLocation", { required: true })} ></Input>
                        {errors.businessLocation && <span>This field is required</span>}
                        <Input placeholder={'Enter your business phone number....'} inputLabel={'Business phone number'} {...register("businessPhoneNumber", { required: true })} ></Input>
                        {errors.businessPhoneNumber && <span>This field is required</span>}
                        <KontinumP opacity={0.8} margin={'20px 0px 32px 0px'}>Already have an account?</KontinumP>

                        <MultiSubContainer padding={'20px'}>
                            <KontinumButton type={'submit'}>Next</KontinumButton>
                        </MultiSubContainer>
                        <MultiSubContainer padding={'40px'}>
                            <KontinumSignature opacity={0.6}>Copyright by Kontinum 2024. All Rights Reserved.</KontinumSignature>
                        </MultiSubContainer>

                    </>
                } { state === RegisterFlow.FIRST_INFO_FILLED &&
                    <>
                        <Input placeholder={'Enter your email....'} inputLabel={'Business email'} {...register("businessEmail", { required: true })} ></Input>
                        {errors.businessEmail && <span>This field is required</span>}
                        <Input type={'password'} placeholder={'Enter your password....'} inputLabel={'Password'} {...register("password", { required: true })} ></Input>
                        {errors.password && <span>This field is required</span>}
                        <Input type={'password'} placeholder={'Enter your password again....'} inputLabel={'Verify password'} {...register("verifyPassword", { required: true })} ></Input>
                        {errors.verifyPassword && <span>This field is required</span>}
                        <KontinumP cursor={'pointer'} opacity={0.8} margin={'20px 0px 32px 0px'}>Already have an account?</KontinumP>

                        <MultiSubContainer padding={'20px'}>
                            <KontinumButton type={'submit'}>Register</KontinumButton>
                        </MultiSubContainer>
                        <MultiSubContainer padding={'40px'}>
                            <KontinumSignature opacity={0.6}>Copyright by Kontinum 2024. All Rights Reserved.</KontinumSignature>
                        </MultiSubContainer>
                    </>
                }
            </KontinumForm>
        </ContainerRegister>
    )
}