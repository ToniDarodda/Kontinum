import {
  QueryClient,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";

import { PatchUserType } from "../interfaces";
import { userService } from "../services";

const MutationKeyCreateUser = "CREATE_USER_KEY";
const MutationKeyGetUser = "GET_USER_KEY";
const MutationKeyGetAllUser = "GET_ALL_USER_KEY";
const MutationKeyPatchUser = "PATCH_USER_KEY";
const MutationKeyDeleteUser = "DELETE_USER_KEY";
export const useCreateUser = () => {
  const queryClient: QueryClient = useQueryClient();

  return useMutation({
    mutationKey: [MutationKeyCreateUser],
    mutationFn: userService.createUser,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: [MutationKeyGetAllUser],
      });
    },
  });
};

export const useGetUser = (userId: string) =>
  useQuery({
    queryKey: [MutationKeyGetUser, userId],
    queryFn: () => userService.getUser(userId),
  });

export const useGetUsers = () =>
  useQuery({
    queryKey: [MutationKeyGetAllUser],
    queryFn: userService.getAllUser,
  });

export const usePatchUser = () =>
  useMutation({
    mutationKey: [MutationKeyPatchUser],
    mutationFn: (params: { userId: string; data: PatchUserType }) =>
      userService.patchUser(params.userId, params.data),
  });

export const useDeleteUser = () => {
  const queryClient: QueryClient = useQueryClient();

  return useMutation({
    mutationKey: [MutationKeyDeleteUser],
    mutationFn: userService.deleteUser,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: [MutationKeyGetAllUser],
      });
    },
  });
};
