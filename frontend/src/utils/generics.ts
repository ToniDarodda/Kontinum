export type PartialPlusId<T, K> = {
    +readonly [Props in Exclude<keyof T, K>]?: T[Props]
} & {
    readonly id: string;
}

export type PartialPlusReadonly<T> = {
    +readonly [Props in keyof T]?: T[Props]
}

export type Writable<T> = {
    -readonly [Props in keyof T]: T[Props]
}

export type RemoveOptional<T> = {
    [Props in keyof T]-?: T[Props]
}

export type RemoveOptionalAndWritable<T> = {
    -readonly [P in keyof T]-?: T[P]
}