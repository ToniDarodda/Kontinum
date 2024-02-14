export const BaseUser: string = "/user"

export const CreateUser: String = BaseUser;

export const GetUser = (userId: string): string =>  BaseUser + `/${userId}`

export const PatchUser = (userId: string): string => BaseUser + `/${userId}`

export const DeleteUser = (userId: string): string => BaseUser + `/${userId}`