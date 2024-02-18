import React from "react";
import Navbar from "../components/navbar/navbar";
import {NavigationButtonOption} from "../components/navbar/navigation/navigation";
import {KontinumDiv, MultiSubContainer} from "../global/global.style";
import {Stats} from "../components/stats/stats";
import styled from "styled-components";
import {KontinumButton} from "../components/button/button.style";
import {Input} from "../components/input/input";
import {useCreateUser, useGetUsers} from "../query";
import {SubmitHandler, useForm} from "react-hook-form";
import {KontinumForm} from "../components/form/form";
import {KontinumP} from "../components/title/title.style";


const Div = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 20px;
    width: 100%;
    background-color: #FCFBFB;
    margin: 8px 4px;
    border-radius: 8px;
    border: 1px solid #ECEBEB;
`;

type Inputs = {
    firstName: string;
    lastName: string;
    email: string;
}
export function Users(): React.ReactElement {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Inputs>()

    const { mutate: createUser } = useCreateUser()
    const { data } = useGetUsers()

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        createUser({
            ...data,
        });
    }

        return (
        <>
            <Navbar defaultOption={NavigationButtonOption.USERS}/>
            <KontinumDiv>
                <Div>
                    <KontinumForm onSubmit={handleSubmit(onSubmit)} style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', gap: '20px', width: '100%'}}>
                        <MultiSubContainer>
                            <Input inputLabel={'firstname'} placeholder={'Enter your first name...'} {...register("firstName", { required: true })}></Input>
                        </MultiSubContainer>
                        <MultiSubContainer>
                            <Input inputLabel={'Lastname'} placeholder={'Enter your last name...'} {...register("lastName", { required: true })}></Input>
                        </MultiSubContainer>
                        <MultiSubContainer>
                            <Input inputLabel={'Email'} placeholder={'Enter your email...'} {...register("email", { required: true })}></Input>
                        </MultiSubContainer>
                        <KontinumButton onClick={handleSubmit(onSubmit)}>New user</KontinumButton>
                    </KontinumForm>
                    {data?.map((d) => {
                        return (

                            <MultiSubContainer>
                                <KontinumP>{d.lastName} {d.firstName}</KontinumP>
                            </MultiSubContainer>
                        )
                    })}
                </Div>
                <Stats />
            </KontinumDiv>
        </>
    )
}