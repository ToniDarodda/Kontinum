import { PartialPlusId } from "../utils";

interface BasePurchaseInterface {
    readonly id: string;
    readonly userId: string;
    readonly businessId: string;
    readonly totalPrice: number;
}

export type CreatePurchaseType = Omit<BasePurchaseInterface, 'id'>

export type GetPurchaseType = Pick<BasePurchaseInterface, 'id'>

export type PatchPurchaseType = PartialPlusId<BasePurchaseInterface, 'id'>

export type DeletePurchaseType = Pick<BasePurchaseInterface, 'id'>