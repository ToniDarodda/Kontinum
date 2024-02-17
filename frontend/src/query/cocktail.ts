import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

import { cocktailService } from "../services";
import { PatchCocktailType } from "../interfaces";

const MutationKeyCreateCocktail: string = 'CREATE_COCKTAIL_KEY';
const MutationKeyGetCocktail: string = 'GET_COCKTAIL_KEY';
const MutationKeyGetAllCocktail: string = 'GET_ALL_COCKTAIL_KEY';
const MutationKeyPatchCocktail: string = 'PATCH_COCKTAIL_KEY';
const MutationKeyDeleteCocktail: string = 'DELETE_COCKTAIL_KEY';

export const useCreateCocktail = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationKey: [MutationKeyCreateCocktail],
        mutationFn: cocktailService.createCocktail,
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllCocktail] })
        }
    })
}

export const useGetCocktail = (id: string) => useQuery({
    queryKey: [MutationKeyGetCocktail, id],
    queryFn: () => cocktailService.getCocktail(id)
})

export const useGetAllCocktail = () => useQuery({
    queryKey: [MutationKeyGetAllCocktail],
    queryFn: cocktailService.getAllCocktail
})

export const usePatchCocktail = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationKey: [MutationKeyPatchCocktail],
        mutationFn: (params: {
            cocktailId: string;
            patchData: PatchCocktailType
        }) => cocktailService.patchCocktail(params.cocktailId, params.patchData),
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllCocktail] })
        }
    })
}

export const useDeleteCocktail = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationKey: [MutationKeyDeleteCocktail],
        mutationFn: cocktailService.deleteCocktail,
        onSuccess: data => {
            queryClient.invalidateQueries({ queryKey: [MutationKeyGetAllCocktail] })
        }
    })
}
