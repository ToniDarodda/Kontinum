import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { businessService } from "../services/business";

const MutationKeyCreateBusiness: string = 'CREATE_BUSINESS_KEY';
const MutationKeyGetBusiness: string = 'GET_BUSINESS_KEY';
const MutationKeyPatchBusiness: string = 'PATCH_BUSINESS_KEY';
const MutationKeyDeleteBusiness: string = 'DELETE_BUSINESS_KEY';
const MutationKeyLoginBusiness: string = 'LOGIN_BUSINESS_KEY';

export const useCreateBusiness = () => useMutation({
    mutationKey: [MutationKeyCreateBusiness],
    mutationFn: businessService.createBusiness,
    onError: (error: Error) => console.log(error),
});

export const useLoginBusiness = () => useMutation({
    mutationKey: [MutationKeyLoginBusiness],
    mutationFn: businessService.loginBusiness
})

export const useGetBusiness = (id: string) => useQuery({
    queryKey: [MutationKeyGetBusiness, id],
    queryFn: () => businessService.getBusiness(id),
});

export const usePatchBusiness = () => {
    const queryClient = useQueryClient();

    return useMutation({

        mutationKey: [MutationKeyPatchBusiness],
        mutationFn: businessService.patchBusiness,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetBusiness] })
        }
    });
}

export const useDeleteBusiness = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationKey: [MutationKeyDeleteBusiness],
        mutationFn: businessService.deleteBusiness,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetBusiness, MutationKeyCreateBusiness]})
        }
    });
}
