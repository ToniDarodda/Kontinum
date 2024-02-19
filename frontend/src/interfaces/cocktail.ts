import { PartialPlusId } from "../utils";

interface BaseCocktailInterface {
  readonly id: string;
  readonly name: string;
  readonly pricePerServing: string;
}

export type CocktailType = BaseCocktailInterface;

export type CreateCocktailType = Omit<BaseCocktailInterface, "id">;

export type GetCocktailType = Pick<BaseCocktailInterface, "id">;

export type PatchCocktailType = PartialPlusId<BaseCocktailInterface, "id">;

export type DeleteCocktailType = Pick<BaseCocktailInterface, "id">;
