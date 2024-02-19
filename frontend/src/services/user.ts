import { CreateUserType, PatchUserType, UserType } from "../interfaces";
import { Fetch } from "../utils";

class UserService {
  async createUser(userData: CreateUserType): Promise<UserType> {
    const { data: user }: { data: UserType } = await Fetch.post<UserType>(
      "/user",
      userData,
    );

    return user;
  }

  async getUser(userId: string): Promise<UserType> {
    const { data: user }: { data: UserType } = await Fetch.get<UserType>(
      `/user/${userId}`,
    );

    return user;
  }

  async getAllUser(): Promise<UserType[]> {
    const { data: users }: { data: UserType[] } =
      await Fetch.get<UserType[]>("/user");
    return users;
  }

  async patchUser(userId: string, userData: PatchUserType): Promise<number> {
    const { data: objectModified }: { data: number } =
      await Fetch.patch<number>(`/user/${userId}`, userData);

    return objectModified;
  }

  async deleteUser(userId: string): Promise<void> {
    await Fetch.delete<void>(`/user/${userId}`);
  }
}

export const userService: UserService = new UserService();
