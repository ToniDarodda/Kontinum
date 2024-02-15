import { PartialPlusId } from "../utils";

interface BaseUserInterface {
    readonly id: string
    readonly firstName: string;
    readonly lastName: string;
    readonly email: string;
    readonly password: string;
    readonly businessId: string;
}

export type CreateUserType = Omit<BaseUserInterface, 'id'>

export type GetUserType = Pick<BaseUserInterface, 'id'>

export type PatchUserType = PartialPlusId<BaseUserInterface, 'id'>

export type DeleteUserType = Pick<BaseUserInterface, 'id'>