import { QueryClient, useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

import { stockService } from "../services";
import { PatchStockType } from "../interfaces";

const MutationKeyCreateStock: string = 'CREATE_STOCK_KEY';
const MutationKeyGetStock: string = 'GET_STOCK_KEY';
const MutationKeyGetAllStock: string = 'GET_ALL_STOCK_KEY';
const MutationKeyPatchStock: string = 'PATCH_STOCK_KEY';
const MutationKeyDeleteStock: string = 'DELETE_STOCK_KEY';
export const useCreateStock = () => {
    const queryClient: QueryClient = useQueryClient()

    return useMutation({
        mutationKey: [MutationKeyCreateStock],
        mutationFn: stockService.createStock,
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllStock] })
        }
    })
}

export const useGetStock = (id: string) => useQuery({
    queryKey: [MutationKeyGetStock, id],
    queryFn: () => stockService.getStock(id)
})

export const useGetStocks = () => useQuery({
    queryKey: [MutationKeyGetAllStock],
    queryFn: stockService.getAllStock
})

export const usePatchStock = () => {
    const queryClient: QueryClient = useQueryClient()

    return useMutation({
        mutationKey: [MutationKeyPatchStock],
        mutationFn: (params: { id: string; patchData: PatchStockType }) => stockService.patchStock(params.id, params.patchData),
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllStock] })
        }
    })
}

export const useDeleteStock = () => {
    const queryClient: QueryClient = useQueryClient()

    return useMutation({
        mutationKey: [MutationKeyDeleteStock],
        mutationFn: stockService.deleteStock,
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllStock] })
        }
    })
}