import React from "react";

import { SubmitHandler, useForm } from "react-hook-form";

import { KontinumButton } from "../components/button/button.style";
import { UserCard } from "../components/card/user/user.style";
import { KontinumForm } from "../components/form/form";
import { Input } from "../components/input/input";
import Navbar from "../components/navbar/navbar";
import { NavigationButtonOption } from "../components/navbar/navigation/navigation";
import { Stats } from "../components/stats/stats";
import { KontinumP } from "../components/title/title.style";
import {
  KontinumDiv,
  MultiSubContainer,
  UserDiv,
} from "../global/global.style";
import { useCreateUser, useGetUsers } from "../query";

type Inputs = {
  firstName: string;
  lastName: string;
  email: string;
};
export function Users(): React.ReactElement {
  const { register, handleSubmit } = useForm<Inputs>();

  const { mutate: createUser } = useCreateUser();
  const { data } = useGetUsers();

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    createUser({
      ...data,
    });
  };

  return (
    <>
      <Navbar defaultOption={NavigationButtonOption.USERS} />
      <KontinumDiv>
        <UserDiv>
          <KontinumForm
            onSubmit={handleSubmit(onSubmit)}
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              gap: "20px",
              flex: 1,
              width: "100%",
            }}
          >
            <MultiSubContainer>
              <Input
                inputLabel={"firstname"}
                placeholder={"Enter your first name..."}
                {...register("firstName", { required: true })}
              ></Input>
            </MultiSubContainer>
            <MultiSubContainer>
              <Input
                inputLabel={"Lastname"}
                placeholder={"Enter your last name..."}
                {...register("lastName", { required: true })}
              ></Input>
            </MultiSubContainer>
            <MultiSubContainer>
              <Input
                inputLabel={"Email"}
                placeholder={"Enter your email..."}
                {...register("email", { required: true })}
              ></Input>
            </MultiSubContainer>
            <KontinumButton onClick={handleSubmit(onSubmit)}>
              New user
            </KontinumButton>
          </KontinumForm>
          <MultiSubContainer
            style={{
              display: "flex",
              justifyContent: "normal",
              flexDirection: "column",
              flex: 1,
              width: "100%",
              gap: "10px",
            }}
          >
            <MultiSubContainer
              style={{
                gap: "20px",
                overflowY: "auto",
                maxHeight: "360px",
                justifyContent: "normal",
                width: "90%",
                padding: "8px",
                borderRadius: "8px",
                boxShadow: "rgba(99, 99, 99, 0.2) 0 2px 8px 0",
              }}
            >
              {data?.map((d) => {
                return (
                  <UserCard key={d.id}>
                    <KontinumP color={"#ffffff"}>
                      {d.lastName} {d.firstName}
                    </KontinumP>
                  </UserCard>
                );
              })}
            </MultiSubContainer>
          </MultiSubContainer>
        </UserDiv>
        <Stats />
      </KontinumDiv>
    </>
  );
}
