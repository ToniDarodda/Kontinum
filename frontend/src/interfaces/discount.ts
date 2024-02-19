import { PartialPlusId } from "../utils";

interface BaseDiscountInterface {
  readonly id: string;
  readonly discountPerServing: number;
  readonly onSeveral: number;
  readonly businessId: string;
}

export type DiscountType = BaseDiscountInterface;

export type CreateDiscountType = Omit<BaseDiscountInterface, "id">;

export type GetDiscountType = Pick<BaseDiscountInterface, "id">;

export type PatchDiscountType = PartialPlusId<BaseDiscountInterface, "id">;

export type DeleteDiscountType = Pick<BaseDiscountInterface, "id">;
