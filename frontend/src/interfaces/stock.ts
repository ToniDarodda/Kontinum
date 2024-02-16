import { PartialPlusId } from "../utils";

interface BaseStockInterface {
    readonly id: string;
    readonly capacity: number;
    readonly cocktailId: string;
}

export type CreateStockType = Omit<BaseStockInterface, 'id'>

export type GetStockType = Pick<BaseStockInterface, 'id'>

export type PatchStockType = PartialPlusId<BaseStockInterface, 'id'>

export type DeleteStockType = Pick<BaseStockInterface, 'id'>