<script setup>
defineProps({ article: Object })
</script>

<template>
  <tr class="text-center">
    <th scope="row">{{ article.articleId }}</th>
    <td class="text-start">
      <router-link
        :to="{ name: 'article-detail', params: { articleId: article.articleId } }"
        class="article-title link-dark"
      >
        {{ article.subject }}
      </router-link>
    </td>
    <td>{{ article.userNickname }}</td>
    <td>{{ article.hit }}</td>
    <td>{{ article.updatedAt }}</td>
    <td style="display: none;">{{ article.userId }}</td>
    </tr>

</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
