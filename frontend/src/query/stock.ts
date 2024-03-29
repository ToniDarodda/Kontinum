import {
  QueryClient,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";

import { PatchStockType } from "../interfaces";
import { stockService } from "../services";

const MutationKeyCreateStock = "CREATE_STOCK_KEY";
const MutationKeyGetStock = "GET_STOCK_KEY";
const MutationKeyGetAllStock = "GET_ALL_STOCK_KEY";
const MutationKeyPatchStock = "PATCH_STOCK_KEY";
const MutationKeyDeleteStock = "DELETE_STOCK_KEY";
export const useCreateStock = () => {
  const queryClient: QueryClient = useQueryClient();

  return useMutation({
    mutationKey: [MutationKeyCreateStock],
    mutationFn: stockService.createStock,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: [MutationKeyGetAllStock],
      });
    },
  });
};

export const useGetStock = (id: string) =>
  useQuery({
    queryKey: [MutationKeyGetStock, id],
    queryFn: () => stockService.getStock(id),
  });

export const useGetStocks = () =>
  useQuery({
    queryKey: [MutationKeyGetAllStock],
    queryFn: stockService.getAllStock,
  });

export const usePatchStock = () => {
  const queryClient: QueryClient = useQueryClient();

  return useMutation({
    mutationKey: [MutationKeyPatchStock],
    mutationFn: (params: { id: string; patchData: PatchStockType }) =>
      stockService.patchStock(params.id, params.patchData),
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: [MutationKeyGetAllStock],
      });
    },
  });
};

export const useDeleteStock = () => {
  const queryClient: QueryClient = useQueryClient();

  return useMutation({
    mutationKey: [MutationKeyDeleteStock],
    mutationFn: stockService.deleteStock,
    onSuccess: async () => {
      await queryClient.invalidateQueries({
        queryKey: [MutationKeyGetAllStock],
      });
    },
  });
};
