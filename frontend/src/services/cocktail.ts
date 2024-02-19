import {
  CocktailType,
  CreateCocktailType,
  PatchCocktailType,
} from "../interfaces";
import { Fetch } from "../utils";

class CocktailService {
  async createCocktail(
    cocktailData: CreateCocktailType,
  ): Promise<CocktailType> {
    const { data: cocktail }: { data: CocktailType } =
      await Fetch.post<CocktailType>("/cocktail", cocktailData);

    return cocktail;
  }

  async getCocktail(id: string): Promise<CocktailType> {
    const { data: cocktail }: { data: CocktailType } =
      await Fetch.get<CocktailType>(`/cocktail/${id}`);

    return cocktail;
  }

  async getAllCocktail(): Promise<CocktailType[]> {
    const { data: cocktail }: { data: CocktailType[] } =
      await Fetch.get<CocktailType[]>("/cocktail");

    return cocktail;
  }

  async patchCocktail(
    id: string,
    patchData: PatchCocktailType,
  ): Promise<number> {
    const { data: objectModified }: { data: number } =
      await Fetch.patch<number>(`/cocktail/${id}`, patchData);

    return objectModified;
  }

  async deleteCocktail(id: string): Promise<string> {
    const { data: textInfo }: { data: string } = await Fetch.delete<string>(
      `/cocktail/${id}`,
    );

    return textInfo;
  }
}
export const cocktailService: CocktailService = new CocktailService();
