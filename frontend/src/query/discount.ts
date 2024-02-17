import { QueryClient, useMutation, useQuery } from "@tanstack/react-query";

import { discountService } from "../services";
import { PatchDiscountType } from "../interfaces";

const MutationKeyCreateDiscount: string = 'CREATE_DISCOUNT_KEY';
const MutationKeyGetDiscount: string = 'GET_DISCOUNT_KEY';
const MutationKeyGetAllDiscount: string = 'GET_ALL_DISCOUNT_KEY';
const MutationKeyPatchDiscount: string = 'PATCH_DISCOUNT_KEY';
const MutationKeyDeleteDiscount: string = 'DELETE_DISCOUNT_KEY';

export const useCreateDiscount = () => {
    const queryClient: QueryClient = new QueryClient()

    return useMutation({
        mutationKey: [MutationKeyCreateDiscount],
        mutationFn: discountService.createDiscount,
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllDiscount] })
        }
    })
}

export const useGetDiscount = (id: string) => useQuery({
    queryKey: [MutationKeyGetDiscount, id],
    queryFn: () => discountService.getDiscount(id)
})

export const useGetDiscounts = () => useQuery({
    queryKey: [MutationKeyGetAllDiscount],
    queryFn: discountService.getAllDiscount
})

export const usePatchDiscount = () => {
    const queryClient: QueryClient = new QueryClient()

    return useMutation({
        mutationKey: [MutationKeyPatchDiscount],
        mutationFn: (params: { userId: string; patchData: PatchDiscountType }) => discountService.patchDiscount(params.userId, params.patchData),
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllDiscount] })
        }
    })
}

export const useDeleteDiscount = () => {
    const queryClient: QueryClient = new QueryClient()

    return useMutation({
        mutationKey: [MutationKeyDeleteDiscount],
        mutationFn: discountService.deleteDiscount,
        onSuccess: data => queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllDiscount] })
    })
}