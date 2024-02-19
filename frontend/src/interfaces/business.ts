import { PartialPlusId } from "../utils";

interface BaseBusinessInterface {
  readonly id: string;
  readonly businessName: string;
  readonly businessLocation: string;
  readonly businessLegalInformation: string;
  readonly businessPhoneNumber: string;
  readonly businessEmail: string;
  readonly password: string;
}

export type BusinessType = Omit<BaseBusinessInterface, "password">;

export type CreateBusinessType = Omit<BaseBusinessInterface, "id">;

export type LoginBusinessType = Pick<BaseBusinessInterface, "password"> & {
  mail: string;
};

export type GetBusinessType = Pick<
  BaseBusinessInterface,
  "id" | "businessEmail"
>;

export type PatchBusinessType = PartialPlusId<BaseBusinessInterface, "id">;

export type DeleteBusinessType = Pick<BaseBusinessInterface, "id">;
